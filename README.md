# a-learning-web
前言：
使用Springboot+vue架构进行前后端链接和mybaits库链接数据库，里面使用python的beautifulsoup，request，msedge.selenium_tools库进行动态网页爬虫，pca算法的人脸特征提取分析以及讯飞星火提供的api接口进行链接构造的简单的学习页面。

效果视频：  
https://github.com/wangchuan0225/a-learning-web.git

介绍：
首先登录界面需要输入密码和账号，点击登录后调用pca人脸识别程序，调用电脑摄像头进行人脸识别。
![Alt text](/picture/p1.png)
当识别为合法人员并且账号密码正确时跳转到主页，主页目前只包含五部分，退出，python学习，java学习，以及google界面，在每个界面下面都加上了讯飞星火的api，可以向其询问问题并得到答复。在下图中有进行询问并得到输出的例子
![Alt text](/picture/p2.png)
在java学习，python学习界面中包含一个展示表格和一个search，updata按钮，当按search时从数据库中获取相关内容进行展示，当按updata时使用动态爬虫从网上爬取相关信息以及链接存到数据库。
![Alt text](/picture/p3.png)
![Alt text](/picture/p4.png)
在google页面中可以直接进行google搜索，会将结果结果的标题和链接放到列表中
![Alt text](/picture/p5.png)
功能模块： 

    |--登录  

        |--输入账号密码进行加密传到后端  
    
        |--使用训练好的pca代码进行人脸特征提取  
    
    |--主页面  

        |--退出到登录页  

        |--python学习页  
    
          |--search 从数据库中选择数据进行输出  
      
          |--updata 使用爬虫从网上找到特定文章  
      
          |--ai 进行简单对话和询问建议  
      
        |--java学习页  
    
          |--search 从数据库中选择数据进行输出  
      
          |--updata 使用爬虫从网上找到特定文章  
      
          |--ai 进行简单对话和询问建议  
      
        |--google搜索页  
    
          |--输入进行搜索  

文件结构： 

|--project-back-end 文件夹内包含后端的代码
    |--

