/*
 * Licensed to ElasticSearch and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. ElasticSearch licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.test.unit.index.mapper.path;

import org.elasticsearch.index.mapper.DocumentMapper;
import org.elasticsearch.test.unit.index.mapper.MapperTests;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.elasticsearch.common.io.Streams.copyToStringFromClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

/**
 *
 */
public class PathMapperTests {

    @Test
    public void testPathMapping() throws IOException {
        String mapping = copyToStringFromClasspath("/org/elasticsearch/test/unit/index/mapper/path/test-mapping.json");
        DocumentMapper docMapper = MapperTests.newParser().parse(mapping);

        assertThat(docMapper.mappers().indexName("first1"), notNullValue());
        assertThat(docMapper.mappers().indexName("name1.first1"), nullValue());
        assertThat(docMapper.mappers().indexName("last1"), nullValue());
        assertThat(docMapper.mappers().indexName("i_last_1"), notNullValue());
        assertThat(docMapper.mappers().indexName("name1.last1"), nullValue());
        assertThat(docMapper.mappers().indexName("name1.i_last_1"), nullValue());

        assertThat(docMapper.mappers().indexName("first2"), nullValue());
        assertThat(docMapper.mappers().indexName("name2.first2"), notNullValue());
        assertThat(docMapper.mappers().indexName("last2"), nullValue());
        assertThat(docMapper.mappers().indexName("i_last_2"), nullValue());
        assertThat(docMapper.mappers().indexName("name2.i_last_2"), notNullValue());
        assertThat(docMapper.mappers().indexName("name2.last2"), nullValue());

        // test full name
        assertThat(docMapper.mappers().fullName("first1"), nullValue());
        assertThat(docMapper.mappers().fullName("name1.first1"), notNullValue());
        assertThat(docMapper.mappers().fullName("last1"), nullValue());
        assertThat(docMapper.mappers().fullName("i_last_1"), nullValue());
        assertThat(docMapper.mappers().fullName("name1.last1"), notNullValue());
        assertThat(docMapper.mappers().fullName("name1.i_last_1"), nullValue());

        assertThat(docMapper.mappers().fullName("first2"), nullValue());
        assertThat(docMapper.mappers().fullName("name2.first2"), notNullValue());
        assertThat(docMapper.mappers().fullName("last2"), nullValue());
        assertThat(docMapper.mappers().fullName("i_last_2"), nullValue());
        assertThat(docMapper.mappers().fullName("name2.i_last_2"), nullValue());
        assertThat(docMapper.mappers().fullName("name2.last2"), notNullValue());
    }
}
