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

package com.trievosoftware.giphy4j.util;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlUtilTest {

    private static final Logger log = LoggerFactory.getLogger(UrlUtilTest.class);

    private static final String BASE_URL = "http://www.base.at";

    @Test
    public void testUrlUtilNoParams() {
	assertEquals("http://www.base.at", UrlUtil.buildUrlQuery(BASE_URL, new HashMap<>()));
    }

    @Test
    public void testUrlUtilWithParams() {
	HashMap<String, String> params = new HashMap<>();

	params.put("api_key", "key");
	params.put("q", "cat funny");
	params.put("limit", "100");
	params.put("offset", "1");

	assertEquals("http://www.base.at?q=cat+funny&offset=1&api_key=key&limit=100",
		UrlUtil.buildUrlQuery(BASE_URL, params));
    }
}
