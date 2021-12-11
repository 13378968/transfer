package com.example.demo.service.impl;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Response;
import com.example.demo.service.IDomainNameTransferService;
import com.example.demo.util.Utils;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import lombok.extern.slf4j.Slf4j;


@Service
public class DomainNameTransferService implements IDomainNameTransferService {
	private static final String SHORT_NAME_PREFIX = "https://tu.com/";
	
	private static final Map<String, String> MAP = new HashMap<>();
	
    public Response<String> getShortDomainName(String longDomainName) {
    	HashFunction hashFunction = Hashing.farmHashFingerprint64();
    	HashCode hascode = hashFunction.hashString(longDomainName, Charset.forName("utf-8"));
    	String shortDomain = Utils.numToStr(hascode.asLong(), 8);
    	
    	MAP.put(shortDomain, longDomainName);
    	
   	    return Response.<String>builder()
   	    		.code(0)
   	    		.message("短域名转换成功")
   	    		.data(SHORT_NAME_PREFIX + shortDomain)
   	    		.build();
    }
    
    public Response<String> getLongDomainName(String shortDomainName) {
    	if (StringUtils.isNotBlank(shortDomainName) && shortDomainName.startsWith(SHORT_NAME_PREFIX)) {
    		shortDomainName = shortDomainName.replaceFirst(SHORT_NAME_PREFIX, ""); 
    	}
    	
   	    if (MAP.containsKey(shortDomainName)) {
   	    	return Response.<String>builder()
   	    			.code(0)
   	    			.message("长域名查询成功")
   	    			.data(MAP.get(shortDomainName))
   	    			.build();  	    	
   	    } else {
   	    	return Response.<String>builder()
   	    			.code(-1)
   	    			.message("长域名查询失败")
   	    			.build();
   	    }
    }
}
