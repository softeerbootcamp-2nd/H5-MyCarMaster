package softeer.mycarmaster.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String healthCheck() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            String hostname = ip.getHostName();
            return "Your current IP address : " + ip + ", Hostname : " + hostname;
        } catch (UnknownHostException e) {
            return "Error retrieving IP and Hostname";
        }
    }
}
