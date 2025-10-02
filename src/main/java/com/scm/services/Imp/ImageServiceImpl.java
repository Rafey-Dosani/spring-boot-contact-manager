package com.scm.services.Imp;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.helper.AppConstants;
import com.scm.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

    private Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile contactImage, String filename) {
       // code uploading 
         
       
       try {
        byte[] data =new byte[contactImage.getInputStream().available()];
        contactImage.getInputStream().read(data);
        cloudinary.uploader().upload(data, ObjectUtils.asMap(
            "public_id",filename
        ));
        
         return this.getUrlFromPublicId(filename);

       }
       
       catch (IOException e) {
    
        e.printStackTrace();
         return null;
    }
       }


       //retun url 

      

    @Override
    public String getUrlFromPublicId(String publicId) {
       
       return cloudinary
       .url()
       .transformation(
        new Transformation<>()
        .width(AppConstants.Contact_Image_Width)
        .height(AppConstants.Contact_Image_Height)
        .crop(AppConstants.Contact_Image_crop)
       )
       .generate(publicId);
    }



}
