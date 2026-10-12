package iuh.fit.nguyenhoang_ontap;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {
	public HelloApplication() {
		// register resource classes in this package
		packages("iuh.fit.nguyenhoang_ontap");
	}
}