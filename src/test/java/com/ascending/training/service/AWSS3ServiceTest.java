package com.ascending.training.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;

import com.ascending.training.init.AppInitializer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class AWSS3ServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AWSS3Service s3Service;

    @Value("${aws.s3.bucket}")
    private String testBucketName;

    @Value("${aws.region}")
    private String awsRegion;

    @Test
    public void isBucketExisTest(){
        String bucketname = "bill-ascending";
        boolean isExist = s3Service.isBucketExist(bucketname);
        assertEquals(isExist, true);
    }

    @Test
    public void testUploadMultipartFile() throws IOException {
        String filename = "/Users/wuminfeng/File2.txt";
        File file = new File(filename);
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("multipartFile",
                file.getName(), "text/plain", IOUtils.toByteArray(input));
        String objecyKey = s3Service.uploadMultipartFile(testBucketName, multipartFile);
        logger.info("===Upload multipartfile name is: {}", objecyKey);

//        List<String> objectKeyList = s3Servie.findObjectKeyList(testBucketName);
//
//        assertThat(true, is(ObjectKeyList.contains(objecyKey)));
    }


    //Study for later use
    @Test
    public void testPresignedURLForUploading()  {
//        String bucketName = "mgao-s3-bucket-1";
        String filename = "bucket-1/images/plain-text-uploaded.txt";
        String urlString = s3Service.generatePresignedURLForUploading(testBucketName, filename);
        logger.info("==== testBucketName ={}", testBucketName);
        logger.info("==== PUT presigneURL string ={}", urlString);
        try {
            URL url = new URL(urlString);
            // Create the connection and use it to upload the new object using the pre-signed URL.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
//            connection.setRequestMethod("POST");
            connection.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write("This text uploaded as an object via presigned URL.");
            out.close();
            // Check the HTTP response code. To complete the upload and make the object available,
            // you must interact with the connection object in some way.
            connection.getResponseCode();
            logger.info("HTTP response code: " + connection.getResponseCode());
        } catch (MalformedURLException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            logger.error(e.getMessage());
        }catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            logger.error(e.getMessage());
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            logger.error(e.getMessage());
        } catch (IOException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            logger.error(e.getMessage());
        }
    }
    @Test
    public void testPresignedURLForDownloading() {
//        String bucketName = "mgao-s3-bucket-1";
//        String filename = "bucket-1/my-folder/my-sub-folder/my-text-file.txt";
//        String destFilename = "/home/michael/Documents/downloads/my-text-file-downloaded.txt";
        String filename = "bucket-1/images/star.jpg";
        String destFilename = "/home/michael/Documents/downloads/star-image-3.jpg";
        String urlString = s3Service.generatePresignedURLForDownloading(testBucketName, filename);
        logger.info("==== GET presigneURL string ={}", urlString);
        try {
            URL url = new URL(urlString);
            // Create the connection and use it to upload the new object using the pre-signed URL.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            File destFile = new File(destFilename);
            FileUtils.touch(destFile);
            InputStream is = connection.getInputStream();
            FileUtils.copyInputStreamToFile(is, destFile);
            // Check the HTTP response code. To complete the upload and make the object available,
            // you must interact with the connection object in some way.
            connection.getResponseCode();
            logger.info("HTTP response code: " + connection.getResponseCode());
            IOUtils.closeQuietly(is);
            IOUtils.close(connection);
        } catch (MalformedURLException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            logger.error(e.getMessage());
        }catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            logger.error(e.getMessage());
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            logger.error(e.getMessage());
        } catch (IOException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            logger.error(e.getMessage());
        }
    }
    @Test
    public void testDirectlyUsingPresignedURLForDownloading() {
//        String bucketName = "mgao-s3-bucket-1";
//        String filename = "presignedTest.txt";
        String destFilename = "user/wuminfeng/test.png";
        String urlString = "https://debug-14-1.s3.amazonaws.com/bucket-1/images/green-square.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20201007T204251Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3599&X-Amz-Credential=AKIAUH7NDCSMB4JXR7OC%2F20201007%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=674badfe9d797881073682e28c856baf6431c41fabb849bc39344d695c03051e";
        logger.info("==== GET presigneURL string ={}", urlString);
        try {
            URL url = new URL(urlString);
            // Create the connection and use it to upload the new object using the pre-signed URL.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            File destFile = new File(destFilename);
            FileUtils.touch(destFile);
            InputStream is = connection.getInputStream();
            FileUtils.copyInputStreamToFile(is, destFile);
            // Check the HTTP response code. To complete the upload and make the object available,
            // you must interact with the connection object in some way.
            connection.getResponseCode();
            logger.info("HTTP response code: " + connection.getResponseCode());
            IOUtils.closeQuietly(is);
            IOUtils.close(connection);
        } catch (MalformedURLException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            logger.error(e.getMessage());
        }catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            logger.error(e.getMessage());
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            logger.error(e.getMessage());
        } catch (IOException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            logger.error(e.getMessage());
        }
    }
}
