/*
 * The MIT License
 *
 * Copyright (c) 2019 Trievo, LLC. https://trievosoftware.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 *
 */
package com.trievosoftware.giphy4j.dao;

import static org.junit.Assert.assertFalse;

import java.io.IOException;

import com.trievosoftware.giphy4j.http.Request;
import com.trievosoftware.giphy4j.http.Response;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRequestSenderTest {
    
    private static final Logger log = LoggerFactory.getLogger(AbstractRequestSenderTest.class);
    
    private RequestSender sender;
    
    protected void setSender(RequestSender sender) {
	this.sender = sender;
    }
    
    private static final String SIMPLE_URL = "http://www.google.at";
    private static final String RANDOM_URL_SUCCESS = "http://api.giphy.com/v1/gifs/random?api_key=dc6zaTOxFJmzC&tag=cat";
    private static final String RANDOM_URL_FORBIDDEN = "http://api.giphy.com/v1/gifs/random?api_key=noKey&tag=cat";
    
    @Test
    public void testSimpleUrl() throws IOException {
	log.info("Test: SimpleUrl - URL: " + SIMPLE_URL);
	
	Response response = sender.sendRequest(new Request(SIMPLE_URL));
	
	assertFalse(response.getHeaders().isEmpty());
	assertFalse(response.getBody().isEmpty());
    }
    
    @Test
    public void testRandomUrlSuccess() throws IOException {
	log.info("Test: RandomUrlSuccess - URL: " + RANDOM_URL_SUCCESS);
	
	Response response = sender.sendRequest(new Request(RANDOM_URL_SUCCESS));
	
	assertFalse(response.getHeaders().isEmpty());
	assertFalse(response.getBody().isEmpty());
    }
    
    @Test(expected = IOException.class)
    public void testRandomUrlForbidden() throws IOException {
	log.info("Test: RandomUrlForbidden - URL: " + RANDOM_URL_FORBIDDEN);
	
	sender.sendRequest(new Request(RANDOM_URL_FORBIDDEN));
    }
}
