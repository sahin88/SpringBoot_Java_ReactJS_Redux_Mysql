package expense;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.filter.CorsFilter;



import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import expense.filter.AuthFilter;
import expense.model.Events;
import expense.model.User;
import expense.repository.EventRepository;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@SpringBootApplication(scanBasePackages={"expense","expense.service","expense.restcontroller"})

public class ExpenseApplication {
	
	@Autowired
	EventRepository eventrepo;
	
	@Autowired
	JavaMailSender mailSender;

	public static void main(String[] args) {
		SpringApplication.run(ExpenseApplication.class, args);
		
		
	}
	
	
    public void sendRememberMail(User user, Events event) throws UnsupportedEncodingException, MessagingException {
		String subject=event.getName();
		String senderName="Remember me App";
		String mailContent="<p> Dear "+user.getFirstName() +" "+ user.getLastName()  +" ,"+"</p>";
		mailContent+="This is email, which remember the following event!";
		
		mailContent+=event.getDescription();
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper= new MimeMessageHelper(message);
		helper.setFrom("your email",senderName);
		helper.setTo(user.getEmail());
		helper.setSubject(subject);
		helper.setText(mailContent, true);
		mailSender.send(message);		
	}
	
	
	
	@Scheduled( cron="50 31 23 * *  *")
	void someJob() throws InterruptedException, UnsupportedEncodingException, MessagingException {
		
		List<Events> eventlist = eventrepo.findAll();
	
		String pattern = "MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date_today = simpleDateFormat.format(new Date());
		
		for(Events event : eventlist){
			String pattern_database = "MM-dd";
			String date_from_db = simpleDateFormat.format(event.getEventDay());
			
			
			if( date_from_db.equals(date_today)) {
				sendRememberMail(event.getUser(),event);
				System.out.println("event :: "+event.getUser());
			}	
		}
		
	}
	
	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);
		registrationBean.addUrlPatterns("/api/events/*");
	
		return registrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		registrationBean.setFilter(new CorsFilter(source));
		registrationBean.setOrder(0);
		return registrationBean;
	

	

}
}
@Configuration
@EnableScheduling

class EnablingConfiguration {
	
}
