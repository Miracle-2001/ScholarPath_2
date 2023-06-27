package com.example.bjtuview.ui.query;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bjtuview.R;
import com.example.bjtuview.ui.MainActivity;
import com.example.bjtuview.ui.base.BaseFragment;

import com.alibaba.fastjson.JSONObject;
import com.example.bjtuview.ui.query.util.Base64Util;
import com.example.bjtuview.ui.query.util.FileHelper;
import com.example.bjtuview.ui.query.util.GetAccess;
import com.example.bjtuview.ui.query.util.GetPicture;
import com.example.bjtuview.ui.query.util.HttpUtil;
import com.example.bjtuview.ui.query.util.Rotate;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kongzue.dialogx.dialogs.PopMenu;
import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.interfaces.OnMenuItemClickListener;
import com.kongzue.dialogx.style.IOSStyle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



/**
 * 咨询
 */
public class QueryFragment extends BaseFragment {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    private RecyclerView recyclerView;
    private ImageView button,btn_upload;
    private List<Message> messageList;
    private MessageAdapter messageAdapter;
    private View view;
    private EditText mEditText;
    private ArrayList<String> history;
    private SpinKitView spinKitView;

    private String imagePath;
    private Uri imageUri;
    private String base64Image;
    private String accessToken;

    private String cv;
    public static final String API_KEY = "XD1NrZUAgPVLGbFKQwwT4m2Z";
    public static final String SECRET_KEY = "9e6cGZGZKt0gNkDkGdbSaDxymwtOn3rB";
    private int id = 1;


    private View Logout,Info,About;

    // 右上角选项
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.query:
                    id = 1;
                    initMessage(id);
                    break;
                case R.id.interview:
                    id = 2;
                    initMessage(id);
                    break;
                case R.id.cv:
                    id = 3;
                    initMessage(id);
                    break;
            }
            return true;
        }
    };

    public void initMessage(int id){
        history.clear();
        messageList.clear();
        messageAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
        if(id==1){
            addToChat(API.QUERY,"bot");
            if(cv==null || cv.equals("")){
                history.add("Q: " + API.PROMPT_QUERY + "<|endoftext|>\n\n");
            }
            else{
                history.add("Q: " + cv + API.PROMPT_QUERY_CV + "<|endoftext|>\n\n");
            }
        }
        else if(id==2){
            addToChat(API.INTERVIEW,"bot");
            if(cv==null || cv.equals("")) {
                history.add("Q: " + API.PROMPT_INTERVIEW + "<|endoftext|>\n\n");
            }
            else{
                history.add("Q: " + cv + API.PROMPT_INTERVIEW_CV + cv + "<|endoftext|>\n\n");
            }
        }
        else if(id==3){
            addToChat(API.CV,"bot");
            if(cv==null || cv.equals("")) {
                history.add("Q: " + API.PROMPT_CV + "<|endoftext|>\n\n");
            }
            else{
                history.add("Q: " + cv + API.PROMPT_CV_CV + cv + "<|endoftext|>\n\n");
            }
        }
    }

    public void initMessage1(int id){
        history.clear();
        if(id==1){
            if(cv==null || cv.equals("")){
                history.add("Q: " + API.PROMPT_QUERY + "<|endoftext|>\n\n");
            }
            else{
                history.add("Q: " + cv + API.PROMPT_QUERY_CV + "<|endoftext|>\n\n");
            }
        }
        else if(id==2){
            if(cv==null || cv.equals("")) {
                history.add("Q: " + API.PROMPT_INTERVIEW + "<|endoftext|>\n\n");
            }
            else{
                history.add("Q: " + cv + API.PROMPT_INTERVIEW_CV + cv + "<|endoftext|>\n\n");
            }
        }
        else if(id==3){
            if(cv==null || cv.equals("")) {
                history.add("Q: " + API.PROMPT_CV + "<|endoftext|>\n\n");
            }
            else{
                history.add("Q: " + cv + API.PROMPT_CV_CV + cv + "<|endoftext|>\n\n");
            }
        }
    }

    // 初始化view
    @Override
    protected void initViews() {
        spinKitView = view.findViewById(R.id.spin_kit);
        spinKitView.setVisibility(View.GONE);

        initAccessToken();
        recyclerView = view.findViewById(R.id.recyclerView);

        // Create Layout behaves and set it in recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        //====================================

        //====================================
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        mEditText = view.findViewById(R.id.message_text_text);
        button = view.findViewById(R.id.send_btn);
        btn_upload = view.findViewById(R.id.upload);

        history = new ArrayList<>();

        addToChat(API.QUERY,"bot");
        history.add("Q: " + API.PROMPT_QUERY + "<|endoftext|>\n\n");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = mEditText.getText().toString().trim();
                addToChat(question,Message.SEND_BY_ME);
                history.add("Q: " + question + "<|endoftext|>\n\n");
                messageList.add(new Message("正在输入...", Message.SEND_BY_BOT));
                chatgpt(question);
                mEditText.setText("");
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopMenu.show(new String[]{"拍照上传", "相册上传"})
                        .setOnMenuItemClickListener(new OnMenuItemClickListener<PopMenu>() {
                            @Override
                            public boolean onClick(PopMenu dialog, CharSequence text, int index) {
                                if(text=="拍照上传"){
                                    Object[] result = GetPicture.openCamera(getActivity(), getActivity(), imagePath,imageUri);
                                    imagePath = (String)result[0];
                                    imageUri = (Uri) result[1];
                                }
                                else if(text=="相册上传"){
                                    GetPicture.upload(getActivity());
                                }
                                return false;
                            }
                        });
            }
        });
    }

    private static int RESULT_LOAD_IMAGE = 1,REQUEST_CODE_CAMERA = 2;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(1);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            spinKitView.setVisibility(View.VISIBLE);

            imageUri = data.getData();

            imagePath = FileHelper.getFileAbsolutePath(getActivity(), imageUri);
            base64Image = Base64Util.getBase64String(imageUri,getActivity());
            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            int pictureDegree = Rotate.getPictureDegree(imagePath);
            decodedByte = Rotate.rotaingBitmap(pictureDegree,decodedByte);
            base64Image = Base64Util.bitmapToBase64(decodedByte);


//            base64Image = getBase64String(selectedImage);
//            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
//            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//            imageView.setImageBitmap(decodedByte);

            Selfie_anime selfie_anime = new Selfie_anime();
            selfie_anime.execute();



        }

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            spinKitView.setVisibility(View.VISIBLE);
            base64Image = Base64Util.getBase64String(imageUri,getActivity());
            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            int pictureDegree = Rotate.getPictureDegree(imagePath);
            decodedByte = Rotate.rotaingBitmap(pictureDegree,decodedByte);
            base64Image = Base64Util.bitmapToBase64(decodedByte);

            Selfie_anime selfie_anime = new Selfie_anime();
            selfie_anime.execute();
            spinKitView.setVisibility(View.VISIBLE);

        }
    }

    public void initAccessToken(){
        GetAccess.getAuth(API_KEY, SECRET_KEY, new GetAccess.TokenCallback() {
            @Override
            public void onTokenReceived(final String token) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        accessToken = token;
                    }
                });
            }
        });
    }

    private class Selfie_anime extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            if(accessToken==null) initAccessToken();
            // 请求url
            String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";
            try {
                String imgParam = URLEncoder.encode(base64Image, "UTF-8");

                String param = "image=" + imgParam;

                String result = HttpUtil.post(url, accessToken, param);

                JsonObject jsonObject = (JsonObject) new JsonParser().parse(result);
                JsonElement element = jsonObject.get("words_result");

                if(element==null){
                    return null;
                }

                JsonArray jsonArray = (JsonArray) element;

                result = "";

                for(int i=0;i<jsonArray.size();i++){
                    JsonObject jsonObject1 = (JsonObject) jsonArray.get(i);
                    result += jsonObject1.get("words").getAsString();
                }


                cv = result;
                initMessage1(id);
                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            spinKitView.setVisibility(View.GONE);
            // 处理网络请求返回结果
            if (result == null || result.equals("")) {
                PopTip.build().setStyle(IOSStyle.style()).show("上传失败");
                return;
            }
            SpannableString s = new SpannableString("已上传简历");
            mEditText.setHint(s);
            PopTip.build().setStyle(IOSStyle.style()).show("上传成功");
            System.out.println(result);
        }
    }

    // 把信息加入chat中
    void addToChat (String message, String sendBy){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new Message(message, sendBy));
                messageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
            }
        });
    }

    // chat的回复
    void addResponse(String response){
        messageList.remove(messageList.size()-1);
        addToChat(response, Message.SEND_BY_BOT);
    }

    protected int getLayoutId() {
        return R.layout.fragment_query;
    }


    // 创建时调用
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_query, container, false);
        messageList = new ArrayList<>();
        initViews();
        return view;
    }

    // 连续对话，用对话历史作为prompt
    String buildPrompt(String question){
        StringBuilder prompt = new StringBuilder();
        if(history.size() > API.MAX_LENGTH){
            history.remove(1);
            history.remove(1);
        }
        if(history.size() > 0){
            for(String s: history){
                prompt.append(s);
            }
        }
        prompt.append("Q: ").append(question).append("\n\nA:");
        return prompt.toString();
    }

    // 调用api
    void chatgpt(String question){
        String endpoint;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(API.TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(API.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(API.TIMEOUT, TimeUnit.SECONDS)
                .build();
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("model",API.MODEL);
        jsonObject.put("prompt", buildPrompt(question));
        jsonObject.put("max_tokens",API.MAX_TOKENS);
        jsonObject.put("temperature",API.TEMPERATURE);
        jsonObject.put("top_p",API.TOP_P);
        jsonObject.put("stream",API.STREAM);
        endpoint = API.API_URL;


        List<JSONObject> list = new ArrayList<>();
        JSONObject msg = new JSONObject();
        msg.put("role", "user");
        msg.put("content", jsonObject.getString("prompt"));


        list.add(msg);
        jsonObject.put("messages", list);
        jsonObject.remove("prompt");

        Request request = new Request.Builder()
                .url(endpoint)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + API.API)
                .post(RequestBody.create(MediaType.parse("application/json"),
                        jsonObject.toString()))
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    InputStream inputStream = response.body().byteStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    StringBuilder res = new StringBuilder();
                    try {
                        while ((line = reader.readLine()) != null) {
                            if(line.length()<50){
                                continue;
                            }
                            JSONObject object = JSONObject.parseObject(line.substring(6));
                            JSONObject choices = JSONObject.parseObject(object.getString("choices")
                                    .replace('[',' ')
                                    .replace(']',' '));
                            String s;
                            s = JSONObject.parseObject(choices.getString("delta")).getString("content");
                            if(s!=null) res.append(s);
                        }
                        reader.close();
                        inputStream.close();
                        history.add("A: " + res + "<|endoftext|>\n\n");
                        addResponse(String.valueOf(res));
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    addResponse("Failed to load response due to"+response.body().toString());
                }
            }
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Failed to load response due to"+e.getMessage());
            }
        });

    }

    @Override
    public Toolbar.OnMenuItemClickListener changeMenu() {
        Drawable moreIcon = ContextCompat.getDrawable(MainActivity.toolbar.getContext(), R.drawable.baseline_more_24);
        MainActivity.toolbar.setOverflowIcon(moreIcon);
        MainActivity.toolbar.setTitle("咨询");
        return onMenuItemClick;
    }

    @Override
    public int getMenuID() {
        return R.menu.menu_query;
    }
}
