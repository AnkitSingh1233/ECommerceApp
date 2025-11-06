package in.nareshit.ankit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.nareshit.ankit.util.EmailUtil;

@RestController
public class EmailController {

	@Autowired
	private EmailUtil emailUtil;

	// Runs every minute
	/*
	 * @Scheduled(cron = "0 * * * * *") public void msg() {
	 * System.out.println("Task executed at: " + java.time.LocalTime.now()); }
	 */
	/*
	 * @Scheduled(cron = "0 * * * * *") public void sendMail() { boolean sent =
	 * emailUtil.send("ankitsigh007@gmail.com", "Welcome to Spring Boot Mail",
	 * "This is a test email from Spring Boot"); System.out.println(sent ?
	 * "Mail sent successfully " : "Failed to send mail "); }
	 */

	    @GetMapping("/send")
	    public String sendMail1() {
	        boolean sent = emailUtil.send(
	            "ankitsigh007@gmail.com", 
	            "Welcome to Spring Boot Mail", 
	            "This is a test email from Spring Boot" 
	        );
	        return sent ? "Mail sent successfully " : "Failed to send mail ";
   }

	@PostMapping("/sendWithAttachment")

	public String sendMailWithAttachment(@RequestParam("to") String to, @RequestParam("subject") String subject,
			@RequestParam("text") String text, @RequestParam("file") MultipartFile file) {
		try {
			ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
				@Override
				public String getFilename() {
					return file.getOriginalFilename();
				}
			};

			boolean sent = emailUtil.send(to, subject, text, resource);

			return sent ? "Mail with attachment sent successfully" : "Failed to send mail ";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error sending mail: " + e.getMessage();
		}
	}

}
