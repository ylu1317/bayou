/*
Copyright 2017 Rice University

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package edu.rice.cs.caper.bayou.study;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserStudyTest {
    @Test
    public void testReadLine() throws IOException {
        String filename = "bayou/src/test/resources/study/text.txt";
        String content;

        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line;
        StringBuilder builder = new StringBuilder();
        while((line = br.readLine()) != null) {
            builder.append(line).append("\n");
        }
        content = builder.toString();
        assertEquals("first line\nsecond line\nthird line\n", content);
    }

    @Test
    public void testBinsearch() {
        int[] array = {-10, -5, -2, 3, 9, 27, 30, 100};
        int target = 30;

        int low = 0;
        int high = array.length - 1;
        int result = -1;
        while(low <= high) {
            int mid = (low + high) / 2;
            if(array[mid] == target) {
                result = mid;
                break;
            } else if(array[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        assertEquals(6, result);
    }

}
