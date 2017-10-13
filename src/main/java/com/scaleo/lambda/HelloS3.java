package com.scaleo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.scaleo.lambda.image.ImageProcessor;

public class HelloS3 {

    public void s3EventHandler(S3Event s3Event, Context context) {
        // TODO code here...

        //LambdaLogger logger = context.getLogger();
        //ImageProcessor.createThumbnail(s3Event, logger)
    }
}
