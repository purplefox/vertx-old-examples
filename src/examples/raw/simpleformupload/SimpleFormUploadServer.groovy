/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package simpleformupload;

/**
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */

vertx.createHttpServer(port: 8080).requestHandler { req ->
  if (req.uri().equals("/")) {
    // Serve the index page
    req.response().sendFile("simpleformupload/index.html");
  } else if (req.uri().startsWith("/form")) {
    req.expectMultipart = true
    req.uploadHandler { upload ->
      upload.exceptionHandler { cause ->
        req.response().setChunked(true).writeString("Upload failed").end();
      }

      upload.endHandler {
        req.response().setChunked(true).writeString("Upload successful, you should see the file in the server directory").end();
      }
      upload.streamToFileSystem(upload.filename());
    }
  } else {
    req.response().statusCode = 404;
    req.response().end();
  }
}.listen();

