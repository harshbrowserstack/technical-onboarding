/* Requires the Docker Pipeline plugin */
def params = []

pipeline {
    agent any
        parameters {
            password(name: 'TEST_MANAGEMENT_API_TOKEN', defaultValue: '6fdc64c7-7f37-4163-ab42-786ecf1e6f90', description: 'API Token of your Test Management Account - You can find here: https://test-management.browserstack.com/settings')
            string(name: 'TEST_MANAGEMENT_PROJECT_NAME', defaultValue: 'Technical Onboarding', description: 'Project Name where you want to upload test results, NOTE: If any new project name is defined, Test Management will create a project for you')
            string(name: 'TEST_RUN_NAME', defaultValue: 'Test Run - TestNG cURL - $BUILD_NUMBER', description: 'Name of your Test Run')
            string(name: 'USER_EMAIL', defaultValue: 'agarwal.harshnu@gmail.com', description: 'User Email')
        }
    stages {
        stage('Run Maven Tests') {
            steps {
                catchError {
                    sh '''
                        rm -rf technical-onboarding # name of your github repository
                        git clone https://github.com/harshbrowserstack/technical-onboarding # clone your github repository
                        cd technical-onboarding # cd to your repo
                        M2_HOME="/var/lib/jenkins/workspace/apache-maven-3.6.3/bin" # path to your maven
                        export PATH="$M2_HOME:$PATH" # set maven path if it does not exists
                        mvn clean # clean your maven project
                        # mvn test # run your tests
                        mvn -Dtest=com.browserstack.LocalDriverTest test-compile surefire:test
                    '''
                }
            }
        }
        stage('Upload Reports in Test Management') {
            steps {
                sh '''
                    export JUNIT_XML_FILE_PATH="$(pwd)/technical-onboarding/target/surefire-reports/junitreports/TEST-com.browserstack.LocalDriverTest.xml"

                    curl -k -X POST https://test-management.browserstack.com/api/v1/import/results/xml/junit \
                    -H "API-TOKEN:$TEST_MANAGEMENT_API_TOKEN" \
                    -F project_name="$TEST_MANAGEMENT_PROJECT_NAME" \
                    -F "file_path=@$JUNIT_XML_FILE_PATH" \
                    -F test_run_name="$TEST_RUN_NAME" \
                    -F user_email=$USER_EMAIL
                '''
            }
        }
    }
}
