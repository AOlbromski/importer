package pl.com.importer.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Class BaseResource
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
public class BaseResource implements Serializable {

    public final Logger logger = LoggerFactory.getLogger(getClass());
}
