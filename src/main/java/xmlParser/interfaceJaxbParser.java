package xmlParser;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by OM on 07.10.2016.
 */
public interface interfaceJaxbParser {
    Object getObject(File file, Class c) throws JAXBException;
    void saveObject(File file, Object o) throws JAXBException;
}
