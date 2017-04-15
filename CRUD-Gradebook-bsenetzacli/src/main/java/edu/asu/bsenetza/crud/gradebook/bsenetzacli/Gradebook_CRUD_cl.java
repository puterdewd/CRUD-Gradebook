package edu.asu.bsenetza.crud.gradebook.bsenetzacli;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.lang.invoke.MethodHandles;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gradebook_CRUD_cl {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final WebResource webResource;
    private final Client client;
    private static final String BASE_URI = "http://localhost:8080/CRUD-Gradebook-bsenetzasrv/webresources";

    public Gradebook_CRUD_cl() {
        LOG.info("Creating a Gradebook REST client");

        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("gradebook/item");
        LOG.debug("webResource = {}", webResource.getURI());
    }

    public ClientResponse createGradedItem(Object requestEntity) throws UniformInterfaceException {
        LOG.info("Initiating a Create GradedItem request");
        LOG.debug("XML String = {}", (String) requestEntity);

        return webResource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, requestEntity);
    }

    public ClientResponse deleteGradedItem(String gradedItemId) throws UniformInterfaceException {
        LOG.info("Initiating a Delete request");
        LOG.debug("gradedItemId = {}", gradedItemId);

        return webResource.path(gradedItemId).delete(ClientResponse.class);
    }

    public ClientResponse updateGradedItem(Object requestEntity, String gradedItemId) throws UniformInterfaceException {
        LOG.info("Initiating an Update request");
        LOG.debug("XML String = {}", (String) requestEntity);
        LOG.debug("gradedItemId = {}", gradedItemId);

        return webResource.path(gradedItemId).type(MediaType.APPLICATION_XML).put(ClientResponse.class, requestEntity);
    }

    public <T> T retrieveGradedItem(Class<T> responseType, String gradedItemId) throws UniformInterfaceException {
        LOG.info("Initiating a Retrieve request");
        LOG.debug("responseType = {}", responseType.getClass());
        LOG.debug("gradedItemId = {}", gradedItemId);

        return webResource.path(gradedItemId).accept(MediaType.APPLICATION_XML).get(responseType);
    }

    public ClientResponse createGrade(Object requestEntity, String gradedItemId, String studentId) throws UniformInterfaceException {
        LOG.info("Initiating a Create Grade request");
        LOG.debug("XML String = {}", (String) requestEntity);
        LOG.debug(webResource.path(gradedItemId + "/student/" + studentId).toString());
        return webResource.path(gradedItemId + "/student/" + studentId).type(MediaType.APPLICATION_XML).post(ClientResponse.class, requestEntity);
    }

    public <T> T retrieveGrade(Class<T> responseType, String gradedItemId, String studentId) throws UniformInterfaceException {
        LOG.info("Initiating a Retrieve Grade request");
        LOG.debug("responseType = {}", responseType.getClass());
        LOG.debug("gradedItemId = {}", gradedItemId);
        LOG.debug("studentId = {}", studentId);
        return webResource.path(gradedItemId + "/student/" + studentId).accept(MediaType.APPLICATION_XML).get(responseType);
    }

    public ClientResponse updateGrade(Object requestEntity, String gradedItemId, String studentId) throws UniformInterfaceException {
        LOG.info("Initiating an Update Grade request");
        LOG.debug("XML String = {}", (String) requestEntity);
        LOG.debug("gradedItemId = {}", gradedItemId);
        LOG.debug("studentId = {}", studentId);

        return webResource.path(gradedItemId + "/student/" + studentId).type(MediaType.APPLICATION_XML).put(ClientResponse.class, requestEntity);
    }

    public ClientResponse deleteGrade(String gradedItemId, String studentId) throws UniformInterfaceException {
        LOG.info("Initiating a Delete Grade request");
        LOG.debug("gradedItemId = {}", gradedItemId);
        LOG.debug("studentId = {}", studentId);

        return webResource.path(gradedItemId + "/student/" + studentId).delete(ClientResponse.class);
    }

    public void close() {
        LOG.info("Closing the REST Client");

        client.destroy();
    }
}
