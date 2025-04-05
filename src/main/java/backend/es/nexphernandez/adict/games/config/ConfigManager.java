package backend.es.nexphernandez.adict.games.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class ConfigManager {
    public static class ConfigProperties {
    
        static String path;

        private static final Properties properties = new Properties();

        /**
         * Metodo estatico para obtener una propiedad
         * @param key palabra clave de la propiedad
         * @return la propiedad
         */
        public static String getProperty(String key){
            return properties.getProperty(key);
        }

        /**
         * funcion para obtener el path
         * @param rutaPath ruta del path a verificar
         */
        public static void setPath(String rutaPath){
            File file = new File(rutaPath);
            if (!file.exists() || !file.isFile()) {
                System.out.println("Path:"+file.getAbsolutePath());
            }
            path = rutaPath;
            try {
                FileInputStream input = new FileInputStream(path);
                InputStreamReader isr = new InputStreamReader(input, "UTF-8");
                properties.load(isr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
