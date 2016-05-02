import be.krivi.ucll.ip.domain.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Krivi on 25/04/16.
 */
@RestController
@RequestMapping( "/" )
public class JsonController{
    @Autowired
    NetworkService service;

    //    @RequestMappingg(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    //    public void addSpecialty(@RequestBody Specialty specialty){
    //        service.addSpecialty(specialty);
    //    }

    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public void getSpecialties(){
        //return service.getSpecialties();
    }
}