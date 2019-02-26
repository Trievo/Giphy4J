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

package com.trievosoftware.giphy4j.entity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;

import com.trievosoftware.giphy4j.dao.RequestSender;
import com.trievosoftware.giphy4j.http.Request;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.trievosoftware.giphy4j.Giphy;
import com.trievosoftware.giphy4j.dao.HttpRequestSender;
import com.trievosoftware.giphy4j.entity.search.SearchRandom;
import com.trievosoftware.giphy4j.exception.GiphyException;

public class SerachRandomTest {

    private final static String API_KEY = "dc6zaTOxFJmzC";
    private final static String RESFILE = "src/test/resources/search-random-response.json";

    private StringWriter writer;
    private FileInputStream inputStream;

    private Gson gson;
    private String jsonResponse;

    private Giphy giphy;
    private Giphy giphyMockExceptionCode;
    private Giphy giphyMockExceptionParse;

    @Before
    public void setUp() throws Exception {
	File inputFile = new File(RESFILE);

	inputStream = new FileInputStream(inputFile);
	writer = new StringWriter();

	IOUtils.copy(inputStream, writer, "UTF-8");
	jsonResponse = writer.toString();

	gson = new GsonBuilder().create();
	giphy = new Giphy(API_KEY);

	RequestSender senderCode = Mockito.mock(HttpRequestSender.class);
	when(senderCode.sendRequest(any(Request.class))).thenThrow(new IOException("Bad response! Code: 401"));
	giphyMockExceptionCode = new Giphy(API_KEY, senderCode);

	RequestSender senderParse = Mockito.mock(HttpRequestSender.class);
	when(senderParse.sendRequest(any(Request.class)))
		.thenThrow(new IOException("Unparseable response body! \n { '' }"));
	giphyMockExceptionParse = new Giphy(API_KEY, senderCode);

    }

    @After
    public void tearDown() throws Exception {
	writer.close();
	inputStream.close();
    }

    @Test
    public void testSearchFeedModel() {
	SearchRandom searchRandom = gson.fromJson(jsonResponse, SearchRandom.class);

        assertEquals(200, searchRandom.getMeta().getStatus());
        assertNotNull(searchRandom.getData());
        assertEquals("pZMX1O9D5LHJS", searchRandom.getData().getId());
    }

    @Test
    public void testSearchFeedRequestSearchRandomSuccess() throws GiphyException {
        assertNotNull(giphy.searchRandom("cat"));
        assertNotNull(giphy.searchRandom("cat").getData());
    }
    
    @Test
    public void testSearchFeedRequestSearchRandomStickerSuccess() throws GiphyException {
        assertNotNull(giphy.searchRandomSticker("cat"));
        assertNotNull(giphy.searchRandomSticker("cat").getData());
    }


    @Test(expected = GiphyException.class)
    public void testSearchFeedRequestFailExceptionCode() throws GiphyException {
	giphyMockExceptionCode.searchRandom("");
    }
    
    @Test(expected = GiphyException.class)
    public void testSearchFeedRequestFailExceptionParse() throws GiphyException {
	giphyMockExceptionParse.searchRandomSticker("");
    }

}
