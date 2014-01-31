/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package datafu.pig.text.opennlp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CachedFile {

    public static String getFileName(String modelLink, String modelFile) throws IOException {
        // if the symlink exists, use it, if not, use the raw name if it exists
        // note: this is to help with testing, as it seems distributed cache doesn't work with PigUnit
        String loadFile = modelFile;
        if (!new File(loadFile).exists()) {
            if (new File(modelLink).exists()) {
                loadFile = modelLink;
            } else {
                throw new IOException(String.format("Could not load model, neither symlink %s nor file %s exist", modelFile, modelLink));
            }
        }
        return loadFile;
    }
}
