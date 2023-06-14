package com.example.bjtuview.ui.query;

public class API {
    public static String API_URL = "https://api.openai.com/v1/chat/completions";
    public static String API = "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";
    public static String MODEL = "gpt-3.5-turbo";
    public static Integer MAX_TOKENS = 1000;
    public static Double TEMPERATURE = 0.5;
    public static Integer TOP_P = 1;
    public static boolean STREAM = true;
    public static Integer TIMEOUT = 3600;

    public static Integer MAX_LENGTH = 20;

    public static String QUERY = "同学你好，我是研途GPT，请问你有什么保研的问题？我会根据你的背景给出定制化的分析。" +
            "（通过右下角可以上传你的个人简历，或者使用注册时的背景信息）";
    public static String PROMPT_QUERY = "我是计算机专业大学的学生，背景来自北京交通大学，我有两段科研经历，" +
            "一段是关于利用深度学习进行图像分割，一段利用利用循环神经网络预测交通流量数据" +
            "我是大创负责人" +
            "我有很好的科研产出，一篇AAAI一作，两篇ccf-c类期刊一作" +
            "另外，我还有丰富的竞赛经历，多次获得ICPC区域赛金牌" +
            "我还参加过数学建模国赛和美赛，都拿了较好的名次" +
            "我的专业排名是全专业第一" +
            "我的缺点在于我没有通过国家六级考试"+

            "接下来我要咨询你一些保研的问题，请你给出回答，并结合我的背景给出分析";

    public static String PROMPT_QUERY_CV =
            "以上是我的背景，我要咨询你一些保研的问题，请你给出回答，并结合我的背景给出分析";

    public static String INTERVIEW = "同学你好，我是研途GPT。现在我将作为夏令营面试官，我会根据你的简历对你进行模拟面试，" +
            "以帮助你提前感受夏令营的氛围和发现问题。每当你回答完一个问题之后，我会对其进行评价，" +
            "并根据你的表现在0-100分之间打出一个分数。" +
            "输入“开始提问“开始模拟面试。每个问题结束之后，输入”下一个提问“继续面试。" +
            "（通过右下角可以上传你的个人简历，或者使用注册时的背景信息）";
    public static String PROMPT_INTERVIEW = "我是计算机专业大学的学生，背景来自北京交通大学，我有两段科研经历，" +
            "一段是关于利用深度学习进行图像分割，一段利用利用循环神经网络预测交通流量数据" +
            "我是大创负责人" +
            "我有很好的科研产出，一篇AAAI一作，两篇ccf-c类期刊一作" +
            "另外，我还有丰富的竞赛经历，多次获得ICPC区域赛金牌" +
            "我还参加过数学建模国赛和美赛，都拿了较好的名次" +
            "我的专业排名是全专业第一" +
            "我的缺点在于我没有通过国家六级考试"+

            "接下来我需要你作为夏令营面试官，根据我的简历对我进行模拟面试，面试的问题最好结合我的科研或者获奖进行提问，" +
            "比如我在某一段科研中干了什么事情，或者我在某段竞赛中做了哪些工作" +
            "也可以提问计算机课程的专业知识，比如什么是LRU，页面替换算法有哪些" +
            "采用一问一答的方式，不需要你来回答，你只需要提问题和评价给建议" +
            "每当我回答完一个问题之后，我需要你对我的回答进行评价，给出相关建议，" +
            "并根据我的回答给出在0-100分之间打出一个分数" +
            "输入“开始提问“开始模拟面试。每个问题结束之后，输入”下一个提问“继续面试。";

    public static String PROMPT_INTERVIEW_CV =
            "以上是我的简历，接下来我需要你作为夏令营面试官，根据我的简历对我进行模拟面试，面试的问题最好结合我的科研或者获奖进行提问，" +
            "比如我在某一段科研中干了什么事情，或者我在某段竞赛中做了哪些工作" +
            "也可以提问计算机课程的专业知识，比如什么是LRU，页面替换算法有哪些" +
            "采用一问一答的方式，不需要你来回答，你只需要提问题和评价给建议" +
            "每当我回答完一个问题之后，我需要你对我的回答进行评价，给出相关建议，" +
            "并根据我的回答给出在0-100分之间打出一个分数" +
            "输入“开始提问“开始模拟面试。每个问题结束之后，输入”下一个提问“继续面试。";

    public static String CV = "同学你好，我是研途GPT。现在我将作为夏令营面试官，对你的简历进行评价并给出一些建议，" +
            "最后我会给你的简历在0-100分之间打出一个分数，" +
            "以帮助你更好地了解和完善你的简历。" +
            "请问你对你的简历还有额外补充的内容吗。如果没有，请输入“开始”。" +
            "（通过右下角可以上传你的个人简历，或者使用注册时的背景信息）";
    public static String PROMPT_CV = "我是计算机专业大学的学生，背景来自北京交通大学，我有两段科研经历，" +
            "一段是关于利用深度学习进行图像分割，一段利用利用循环神经网络预测交通流量数据" +
            "我是大创负责人" +
            "我有很好的科研产出，一篇AAAI一作，两篇ccf-c类期刊一作" +
            "另外，我还有丰富的竞赛经历，多次获得ICPC区域赛金牌" +
            "我还参加过数学建模国赛和美赛，都拿了较好的名次" +
            "我的专业排名是全专业第一" +
            "我的缺点在于我没有通过国家六级考试"+

            "接下来我需要你作为夏令营面试官，对我的简历进行评价并给出一些建议，最后请你给我的简历在0-100分之间打出一个分数" +
            "我可能还会补充一些内容，当我输入“开始”的时候开始评价。";

    public static String PROMPT_CV_CV =
            "以上是我的简历，接下来我需要你作为夏令营面试官，对我的简历进行评价并给出一些建议，" +
            "需要你结合我的简历的具体条目，如竞赛或者科研，说明我的优势或者劣势。" +
            "最后请你给我的简历在0-100分之间打出一个分数，并说明打出改分的理由。" +
            "我可能还会补充一些内容，当我输入“开始”的时候开始评价。";
}