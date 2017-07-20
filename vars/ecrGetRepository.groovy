import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder
import com.amazonaws.auth.STSAssumeRoleSessionCredentialsProvider

import com.amazonaws.services.ecr.AmazonECRClientBuilder
import com.amazonaws.services.ecr.model.ListImagesRequest
import com.amazonaws.services.ecr.AmazonECRClient



def awsCredProvider(credentialsId, region, roleArn=null) {
  withCredentials([[
    $class: 'UsernamePasswordMultiBinding',
    credentialsId: credentialsId,
    usernameVariable: 'ACCESS_KEY_ID',
    passwordVariable: 'SECRET_ACCESS_KEY'
  ]]) {
    def aws_cred = new BasicAWSCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY)
    return new AWSStaticCredentialsProvider(aws_cred)
  }
}

def call ( repoName, credentialsId, region) {
  def aws_cred_provider = awsCredProvider(credentialsId, region)
  def ecr_client = AmazonECRClientBuilder.standard().withCredentials(aws_cred_provider).withRegion(region).build()
  //def request = new DescribeRepositoriesRequest().withRepositoryNames(repoName)
  //def request = new DescribeImagesRequest().withRepositoryName(reponame)
  def request = new ListImagesRequest().withRepositoryName(repoName)
  println ecr_client.listImages(request).getImageIds()[0]
  println ">>>>>>>>>>>>>>>>>>>>>>>"
  println ecr_client.listImages(request).getImageIds()
  //println "image ======>"
  //println request
  //return ecr_client.describeImagesRequest(request).getImageIds()

}
