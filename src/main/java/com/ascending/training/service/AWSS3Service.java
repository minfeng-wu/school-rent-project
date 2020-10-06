package com.ascending.training.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AWSS3Service {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.region}")
    private String awsRegion;

    public AWSS3Service(){
        amazonS3 = getS3ClientUsingDefaultChain();
    }

    private AmazonS3 getS3ClientUsingDefaultChain(){
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .build();
        return s3Client;

    }

    public boolean isBucketExist(String bucketName){
        boolean isExist = amazonS3.doesBucketExistV2(bucketName);
        return isExist;
    }

//    public boolean isObjectExist(String bucketName, String objectKey) {
//    }
//
//    public Bucket createBucket(String bucketName) {
//
//    }
//    public List<Bucket> getBucketList() {
//
//    }
//    public void deleteBucket(String bucketName) {
//
//    }
//    public void uploadObject(String bucketName,
//                            String key, String fullFilepath) {
//
//    }
//    public ObjectListing listObjects(String bucketName) {
//
//    }
}
