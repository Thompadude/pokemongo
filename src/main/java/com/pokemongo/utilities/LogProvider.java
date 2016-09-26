package com.pokemongo.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogProvider {
    
    private static Logger logger = null;
    
    protected LogProvider() {}
    
    public static Logger getLogger () {
        if (logger == null) {
            logger = LogManager.getLogger("PokeLog");
        }
        
        return logger;
    }
}
