package com.evaluacion.dispatcher.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.evaluacion.dispatcher.client.dto.CallDto;



@Component
public class DispatcherClient {
	
	public String dispatch(CallDto calldto,String url) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = url;
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<CallDto> entity = new HttpEntity<CallDto>(calldto, headers);
	    ResponseEntity<String> resp=restTemplate.exchange(uri, HttpMethod.POST, entity, String.class, 100);
	    return resp.getBody();
	}
	
	public void freeCall(String idcall,String url) {
		CloseableHttpClient client = HttpClients.custom().build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	    requestFactory.setHttpClient(client);
	    RestTemplate restTemplate =  new RestTemplate(requestFactory);
	    Map<String,Object> uriVariables=new HashMap<String,Object>();
	    uriVariables.put("idcall", idcall);
	    ResponseEntity<String> response=restTemplate.getForEntity(url+"?idCall="+idcall, String.class,uriVariables);
	}
}
