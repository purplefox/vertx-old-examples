/*
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package https

def server =
    vertx.createHttpServer(host: "localhost", port: 4443,
        ssl: true, keyStoreOptions: [path: 'server-keystore.jks', password: 'wibble'])

server.requestHandler { req ->
  req.response().setChunked(true).writeString("<html><body><h1>Hello from vert.x!</h1></body></html>").end()
}.listen()
