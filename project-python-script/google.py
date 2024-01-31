import time
import sys
from msedge.selenium_tools import Edge, EdgeOptions
from bs4 import BeautifulSoup
import pymysql
# 链接数据库
db = pymysql.connect(host='localhost',
                     user='root',
                     password='wang1601',
                     database='learning',
                     charset='utf8')
cursor = db.cursor()

def searchFromgoogle(message):
    edge_options = EdgeOptions()
    # 使用edge的驱动，设置报头
    edge_options.add_argument(
        '--user-agent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0"')
    # 使用字典根据输入转化为网页字符串
    # 拼接url，从google进行链接
    url='https://www.google.com/search?q='+message+'&oq='+message+'&gs_lcrp=EgZjaHJvbWUyDggAEEUYORhDGIAEGIoFMgwIARAAGEMYgAQYigUyDAgCEAAYQxiABBiKBTIHCAMQABiABDIHCAQQABiABDIHCAUQABiABDIHCAYQABiABDIHCAcQABiABDIHCAgQABiABDIHCAkQABiABNIBCTU3NzJqMGoxNagCALACAA&sourceid=chrome&ie=UTF-8'
    # 启动Edge浏览器
    browser = Edge(executable_path="E:\python\pythonProject6\edgedriver_win64/msedgedriver.exe", options=edge_options)
    browser.get(url)
    # 获取网页的HTML内容
    html = browser.page_source

    # 使用BeautifulSoup解析HTML
    soup = BeautifulSoup(html, 'html.parser')
    titles=soup.find_all('h3',class_='LC20lb MBeuO DKV0Md')
    links=soup.find_all('a',jsname='UWckNb')
    title_list=[]
    link_list=[]
    # 获取标题
    for title in titles:
        title_list.append(title.text)
    # 获取链接
    for link in links:
        link_list.append(link.get('href'))
    sql='delete from google where 1'
    cursor.execute(sql)
    db.commit()
    # 将数据插入
    for i in range(0,len(link_list)):
        sql='insert into google (id, title, link) values(%s, %s, %s)'
        cursor.execute(sql,(str(i),title_list[i],link_list[i]))
        db.commit()


if __name__=='__main__':
    script_name = sys.argv[0]
    arguments = sys.argv[1:]
    search_query = ' '.join(arguments)
    searchFromgoogle(search_query)

