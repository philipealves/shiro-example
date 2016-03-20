package br.com.pws.shiro.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.pws.shiro.dto.Contact;
import br.com.pws.shiro.dto.SocialMedia;

@Path("/contact")
public class ContactService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Contact getContact() {
        Contact contact = new Contact();

        contact.setName(generateMemoryName());
        contact.setLocation(generateMemoryLocation());
        contact.setTitle(generateMemoryTitle());

        contact.setResume(generateMemoryResume());
        contact.setSocialMediaList(generateMemorySocialMedia());

        return contact;
    }

    private String generateMemoryName() {
        return "Philipe Alves de Oliveira e Silva";
    }

    private String generateMemoryLocation() {
        return "Uberlândia, Minas Gerais, Brazil";
    }

    private String generateMemoryTitle() {
        return "Java EE Developer";
    }

    private String generateMemoryResume() {
        String strResume = "Experience in software development. Oracle Certified "
            + "Professional Associate (Java SE 7), experience in Java programming "
            + "(Java EE), as well as with similar technologies like Hibernate, JPA, "
            + "JAX-WS, JMS, Maven, Tomcat, JBoss, Wildfly, WebSphere Message Queue (MQ), "
            + "Oracle database, MySQL, Subversion (SVN), GitHub. Web apps "
            + "technologies like AngularJS, Bootstrap 3, HTML 5, CSS3.";
        return strResume;
    }

    private List<SocialMedia> generateMemorySocialMedia() {
        List<SocialMedia> socialMediaList = new ArrayList<SocialMedia>();
        socialMediaList.add(new SocialMedia("https://bitbucket.org/philipe_alves", "btn btn-social-icon btn-bitbucket", "fa fa-bitbucket"));
        socialMediaList.add(new SocialMedia("https://github.com/philipealves", "btn btn-social-icon btn-github", "fa fa-github"));
        socialMediaList.add(new SocialMedia("https://plus.google.com/+PhilipeAlvess", "btn btn-social-icon btn-google-plus", "fa fa-google-plus"));
        socialMediaList.add(new SocialMedia("https://www.instagram.com/philipealvees", "btn btn-social-icon btn-instagram", "fa fa-instagram"));
        socialMediaList.add(new SocialMedia("https://br.linkedin.com/in/philipealves", "btn btn-social-icon btn-linkedin", "fa fa-linkedin"));
        socialMediaList.add(new SocialMedia("https://twitter.com/philipeaosilva", "btn btn-social-icon btn-twitter", "fa fa-twitter"));
        return socialMediaList;
    }

}
