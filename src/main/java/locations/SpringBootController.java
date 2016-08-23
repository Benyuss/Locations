package locations;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;


@Controller
@EnableAutoConfiguration
class SpringBootController implements InitLogger {
	
	private static Logger logger = null;
	
	static {
		try {
			InitLogger.initialize();
		} catch (FileNotFoundException e) {
			logger.log(Level.ERROR, "Can't initialize main's constructor due to loggers configuration file hasn't been found.");
			e.printStackTrace();
		} catch (IOException e) {
			logger.log(Level.ERROR, "Can't initialize main's constructor due to loggers configuration file hasn't been found.");
			e.printStackTrace();
		}
		
		logger = InitLogger.logger[0];
	}	
	
	@GetMapping(value="/geohash")
	public String getUserInput(ModelMap model) {
		    	model.put("command", new Tuple());
		return "UserInput";
	}
	
	@PostMapping(value="/geohash", params="SubmitWithDefault")
	public String defaultUserInput(ModelMap model) {
			model.put("command", tupleFill (48.104564, 20.800041, 6) );
		return "UserInput";
	}
	
	@PostMapping(value = "/geohash", params="Submit")
	public String printHash(@ModelAttribute("user")Tuple tuple,ModelMap model, MultipartFile file) {
		
		GetData.setLat1(tuple.getFirstCoordinate());
		GetData.setLon1(tuple.getSecondCoordinate());
		GetData.setRad1(tuple.getRadius());
		
		CSVScanner scanner = new CSVScanner();
    	if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
//				validateFile(file);
				InputStream inputStream = new ByteArrayInputStream(bytes);
				scanner.scan(inputStream);
			}catch (Exception e) { /*TODO*/ }
    	}
    	else {
    	    try {
    	    	File file1 = new File("coordinates.csv");
        	    FileInputStream input = new FileInputStream(file1);
				scanner.scan(input);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		
		LocationExecute.calculate(scanner);
		model.addAttribute("geoItemList", LocationExecute.getTempArray()[0]);
		model.addAttribute("listSize", LocationExecute.getTempArray()[0].size());
		LocationExecute.tempArray[0] = new ArrayList<Tuple>();
		return "Geohash";
	}
	
    @PostMapping(value = "/geohash", params="reset")
    public ModelAndView method() {
            return new ModelAndView("redirect:geohash");

    }

//    public File convert(MultipartFile file)
//    {    
//        File convFile = new File(file.getOriginalFilename());
//        convFile.createNewFile(); 
//        FileOutputStream fos = new FileOutputStream(convFile); 
//        fos.write(file.getBytes());
//        fos.close(); 
//        return convFile;
//    }
    
    private void validateFile(MultipartFile file) {
    	if (!file.getContentType().equals("text/csv")) {
    		throw new RuntimeException("Only csv files are accepted");
    	}
    }
    
//    	
//		if (!file.isEmpty()) {
//			try {
//				byte[] bytes = file.getBytes();
//
//				// Creating the directory to store file
//				String rootPath = System.getProperty("catalina.home");
//				File dir = new File(rootPath + File.separator + "tmpFiles");
//				if (!dir.exists())
//					dir.mkdirs();
//
//				// Create the file on server
//				File serverFile = new File(dir.getAbsolutePath()
//						+ File.separator + name);
//				BufferedOutputStream stream = new BufferedOutputStream(
//						new FileOutputStream(serverFile));
//				stream.write(bytes);
//				stream.close();
//
//				logger.info("Server File Location="
//						+ serverFile.getAbsolutePath());
//
//				return "You successfully uploaded file=" + name;
//			} catch (Exception e) {
//				return "You failed to upload " + name + " => " + e.getMessage();
//			}
//		} else {
//			return "You failed to upload " + name
//					+ " because the file was empty.";
//		}
//	}
	
    public static void main (String args[]) throws Exception {
        SpringApplication.run(SpringBootController.class, args);
    }
    
    private Tuple tupleFill (double lat, double lon, int rad) {
    	Tuple tuple = new Tuple();
		tuple.setFirstCoordinate(lat); 
		tuple.setSecondCoordinate(lon);
		tuple.setRadius(rad);
		return tuple;
    }
    
}
