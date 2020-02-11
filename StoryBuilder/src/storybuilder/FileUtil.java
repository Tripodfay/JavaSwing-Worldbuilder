package storybuilder;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;


public class FileUtil {
	
	public static final String CHARACTER = "characters";
	public static final String EVENT  = "events";
	public static final String ITEM  = "items";
	public static final String LOCATION  = "locations"; 


        //It took me a while to figure out how to do this. 
        //https://www.tutorialspoint.com/java/java_generics.htm
        //<T Extends Asset> is a statement from "Generics" in java. 
        //It means that T can be any type of whatever it extends. 
        //Using this solved my problem of trying to create multiple methods
        //where "t" extended characters, items, events, and locations separately. 
        
        //this creates a list of strings from Filedata. 
        //This method is used in setFieldData for retrieving objects in the driver class.
	public static <T extends Asset> T getObjData(String type, String fileName) throws Exception {
		List<String> fileData = readFile(fileName,type);
		return setFileData(type, fileData);
	}
	
        // readFile creates a Path object, which is used to locate a file in a file system. 
	public static List<String>  readFile(String fileName,String type) throws IOException {
		Path path = Paths.get(getFilePath(fileName,type));
		return Files.readAllLines(path);
	}

        
        //this writes an asset to a file based ont he user selection, by using stringbuilder. 
	public static  <T extends Asset> T setFileData(String type, List<String> fileData) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
                //create an empty object. 
                Object obj = null;
                
                //create stringbuilder object 
                
		StringBuilder sb = new StringBuilder();
                
                //create key for a for loop 
		String key = "";
                
                //depending on the type of asset, use one of the constructors in a subclass. 
		if(CHARACTER.equalsIgnoreCase(type)) {
			obj = new Characters();	
		} else if(EVENT.equalsIgnoreCase(type)) {
			obj = new Events();
		} else if(ITEM.equalsIgnoreCase(type)) {
			obj = new Items();
		} else if(LOCATION.equalsIgnoreCase(type)) {
		  obj = new Locations();
		}
                
                //Enhanced for loop to loop through the file line by line, 
                // if the line contains a colon, and the key is not empty, 
                //create  a field object, which  information about, and dynamic access to, 
                //a single field of a class or an interface in this case of the obj, and its superclass
                //then set the field to accessible, and write the stringbuilder to string in the object. 
                //if the key is NOT empty, then use ":" as a delimiter, starting at the very next space
                //and append the following information. 
                
               
		for (String line : fileData) {
			
			if(line.contains(":")) {
				if(!key.equals("")) {
				  Field field;
				  try {
				    field = obj.getClass().getDeclaredField(key);
				  }catch(NoSuchFieldException e) {
				    field = obj.getClass().getSuperclass().getDeclaredField(key);
				  }
					field.setAccessible(true);
					field.set(obj, sb.toString());
				}
				key = line.split(":")[0];
				sb = new StringBuilder();
				sb.append(line.split(":").length > 1 ? line.split(":")[1] : "");
				
			}else {
				sb.append(line);
			}
		}
                //End of Advanced for loop. 
                
		Field field;
        try {
          field = obj.getClass().getDeclaredField(key);
          
          //Catch the errors. Fields throw NoSuchFieldException
          //I had to google how to catch that exception. 
          //this means the class doesnt have a specified name. 
        }catch(NoSuchFieldException e) {
          field = obj.getClass().getSuperclass().getDeclaredField(key);
        }
        //set the field to accessible, and return the object(Asset). 
          field.setAccessible(true);
          field.set(obj, sb.toString());
		return (T) obj;
		
	}
	
	//I made this relatively easy on myself since all the files write to a folder in the program package. 
	private static String getFilePath(String fileName,String type) {
		return type + File.separator + fileName + ".txt";
	}
	
        //The bread and butter of the program.
        //My original main goal for this program was to create files that organize
        //your assets, if those files didnt already exist. 
        //this accomplishes that feat.
        
        
	public static void saveFile(Object obj) throws IOException {
            //create strings for the data, name and path of the file. 
	  String fileData;
	  String fileName;
	  String filePath;
          
                //instanceof checks if a reference variable is containing a given type, in this case Characters
		if(obj instanceof Characters) {
		  Characters characters = (Characters) obj;
		  fileData = characters.toString();
		  fileName = characters.getName();
		  filePath = CHARACTER;
                  
                //events
		}else if(obj instanceof Events) {
		  Events events = (Events) obj;
		  fileData = events.toString();
		  fileName = events.getName();
		  filePath = EVENT;
                  
                //items
		}else if(obj instanceof Items) {
		  Items items = (Items) obj;
                  fileData = items.toString();
                  fileName = items.getName();
                  filePath = ITEM;
          
                //locations
		}else if(obj instanceof Locations) {
		  Locations locations = (Locations) obj;
                  fileData = locations.toString();
                  fileName = locations.getName();
                  filePath = LOCATION;
        }else {
          return; 
        }
                
                //https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html
                //create a new directory in the location of the filepath. If that filepath doesnt exist, createDirectory() automatically creates it. 
		Files.createDirectories(Paths.get( filePath));
                
                //creates a new file(or overwrites an old) in the specified file path. Parameters = path to the file,
                //followed by which bytes to write, OpenOpetion configures HOW to create/open object. StandardOpenOption.create creates a file if it exists. 
                //StandardOpenOption.Truncate existing, and its open for write access, its length is reduced to 0.
		Files.write(Paths.get(getFilePath(fileName, filePath)), fileData.getBytes(), new OpenOption[] {StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING});
		
	}

        
	public static List<File> readFileNamesinFolder(String type) throws IOException {
            //Files.walk parameters are a path starting at the type of the asset being created. 
	  return  Files.walk(Paths.get(type))
                  .filter(Files::isRegularFile)
                  .map(Path::toFile)
                  .collect(Collectors.toList());
                  //these are the final steps in processing a stream, filter, map, collect. 
                  //https://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html
                  //this filters out any non-regular files, ensuring the files are normal data storing files. 
                  //Then maps the path to the file, and collects it to a list type summary. 
                  // :: bypasses the need to import things. Since this is the only time i'll need files::isRegularFiles
                  //Instead of importing Java.nio.file.Files.isRegularFile, I can just use (Files::isRegularFile)
                  
	}

}
