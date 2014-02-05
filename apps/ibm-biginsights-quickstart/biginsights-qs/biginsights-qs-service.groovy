/*******************************************************************************
* Copyright (c) 2012 GigaSpaces Technologies Ltd. All rights reserved
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*******************************************************************************/
service {
    extend "../../../services/chef"
    name "biginsights"
    type "APP_SERVER"
    icon "biginsights.png"
    numInstances 1

    compute {
        template "LARGE_RH"
    }

    lifecycle {
		
		details {
			def currPublicIP = context.publicAddress
			def biginsightsURL = "https://${currPublicIP}:9443"
			def biginsightsUser = "biadmin"
			def biginsightsPass = "biadmin"
			
			
			println "biginsights-service.groovy: biginsightsURL is ${biginsightsURL}"
			println "biginsights-service.groovy: biginsightsUser is ${biginsightsUser}"
			println "biginsights-service.groovy: biginsightsPass is ${biginsightsPass}"
			
			
			return [
				"biginsightsURL":"<a href=\"${biginsightsURL}\" target=\"_blank\">${biginsightsURL}</a>" ,
				"biginsightsUser":"${biginsightsUser}" , 
				"biginsightsPass":"${biginsightsPass}" ,
			]
		}
	
	
        startDetectionTimeoutSecs 1240
        startDetection {
            ServiceUtils.isPortOccupied(System.getenv()["CLOUDIFY_AGENT_ENV_PRIVATE_IP"], 389)
        }
    }
}
