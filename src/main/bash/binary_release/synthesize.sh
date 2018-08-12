#!/bin/bash

# Copyright 2017 Rice University
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
BAYOU_JAR="$(ls $SCRIPT_DIR/*.jar)"
java -Dorg.apache.logging.log4j.simplelog.StatusLogger.level=OFF -DconfigurationFile=$SCRIPT_DIR/resources/conf/apiSynthesisServerConfig.properties -cp $BAYOU_JAR edu.rice.cs.caper.bayou.application.api_synthesis_server.ApiSynthesisLocalClient $1 $2 $3 $4 $5 $6
