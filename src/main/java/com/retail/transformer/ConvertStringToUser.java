package com.retail.transformer;

import com.google.gson.Gson;
import com.retail.entity.User;
import org.apache.beam.sdk.transforms.DoFn;

public class ConvertStringToUser extends DoFn<String, User> {

    @ProcessElement
    public void processElement(@Element String json, OutputReceiver<User> receiver) {

        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        receiver.output(user);
    }

}
