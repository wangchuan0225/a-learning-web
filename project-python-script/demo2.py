import time
import sys
from msedge.selenium_tools import Edge, EdgeOptions
from bs4 import BeautifulSoup
import pymysql
# 定义数据库信息
db = pymysql.connect(host='localhost',
                     user='root',
                     password='wang1601',
                     database='learning',
                     charset='utf8')
cursor = db.cursor()


def get_csdn_message(message):
    edge_options = EdgeOptions()
    # 使用edge的驱动，设置报头
    edge_options.add_argument(
        '--user-agent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0"')
    # 使用字典根据输入转化为网页字符串
    dict = {
        '服务器': 'server',
        'python': 'python',
        'java': 'java',
        '前端': 'web',
        '后端': 'back-end',
    }
    str1 = dict[str(message)]
    # 拼接url，加上要查询的内容名称
    url = 'https://www.csdn.net/nav/' + str1
    # 启动Edge浏览器
    browser = Edge(executable_path="E:\python\pythonProject6\edgedriver_win64/msedgedriver.exe", options=edge_options)
    browser.get(url)
    # 获取网页的HTML内容
    html = browser.page_source

    # 使用BeautifulSoup解析HTML
    soup = BeautifulSoup(html, 'html.parser')

    # 找到所有div标签且class=content
    links1 = soup.find_all('div', class_='content')
    # 从上述div标签中找到所有a标签且target=_blank，class=blog
    links2 = [tag.find_all('a', target='_blank', class_='blog') for tag in links1]
    # 找到span标签中class=blog-text的
    titles = soup.find_all('span', class_="blog-text")
    link_set = []
    title_set = []
    # 将href中链接保存到linkset列表中
    for link in links2:
        for l in link:
            link_set.append(l.get('href'))
    # 将title保存到titleset列表中
    for title in titles:
        content = title.text
        title_set.append(content)
    link_list = list(link_set)
    title_list = list(title_set)

    # 构建sql语句，将查询到条目插入数据库
    str2 = "(id, title, link) values(%s, %s, %s)"
    sql = "insert into " + str1 + " " + str2
    for i in range(0, len(link_list)):
        cursor.execute(sql, (str(i), title_list[i], link_list[i]))
        db.commit()
    cursor.close()
    # 关闭连接
    db.close()

if __name__ == "__main__":
    # 从命令行接收信息进行操作
    script_name = sys.argv[0]
    arguments = sys.argv[1:]
    get_csdn_message(arguments[0])
    print(1)

