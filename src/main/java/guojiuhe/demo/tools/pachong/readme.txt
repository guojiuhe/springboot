百度知道首页 ： https://zhidao.baidu.com/
点击我来回答可以进入问题列表 ： https://zhidao.baidu.com/browse
其中有全部问题分类，其中问题分类url格式如下
https://zhidao.baidu.com/list?category=%E5%A8%B1%E4%B9%90%E4%BC%91%E9%97%B2 (娱乐休闲)

https://zhidao.baidu.com/browse 抓取全部问题分类：
分析抓取的dom内容（dom.txt）找到分类信息特点

抓取的分类列表如下
{科技=http://zhidao.baidu.com/list?category=%E7%A7%91%E6%8A%80, 职业=http://zhidao.baidu.com/list?category=%E8%81%8C%E4%B8%9A, 汽车=http://zhidao.baidu.com/list?category=%E6%B1%BD%E8%BD%A6, 政策法规=http://zhidao.baidu.com/list?category=%E6%94%BF%E7%AD%96%E6%B3%95%E8%A7%84, 家电数码=http://zhidao.baidu.com/list?category=%E5%AE%B6%E7%94%B5%E6%95%B0%E7%A0%81, 情感与心理=http://zhidao.baidu.com/list?category=%E6%83%85%E6%84%9F%E4%B8%8E%E5%BF%83%E7%90%86, 母婴=http://zhidao.baidu.com/list?category=%E6%AF%8D%E5%A9%B4, 其他=http://zhidao.baidu.com/list?category=%E5%85%B6%E4%BB%96, 教育培训=http://zhidao.baidu.com/list?category=%E6%95%99%E8%82%B2%E5%9F%B9%E8%AE%AD, 时尚美容=http://zhidao.baidu.com/list?category=%E6%97%B6%E5%B0%9A%E7%BE%8E%E5%AE%B9, 娱乐休闲=http://zhidao.baidu.com/list?category=%E5%A8%B1%E4%B9%90%E4%BC%91%E9%97%B2, 生活=http://zhidao.baidu.com/list?category=%E7%94%9F%E6%B4%BB, 文化历史=http://zhidao.baidu.com/list?category=%E6%96%87%E5%8C%96%E5%8E%86%E5%8F%B2, 医疗健康=http://zhidao.baidu.com/list?category=%E5%8C%BB%E7%96%97%E5%81%A5%E5%BA%B7, 互联网=http://zhidao.baidu.com/list?category=%E4%BA%92%E8%81%94%E7%BD%91, 游戏=http://zhidao.baidu.com/list?category=%E6%B8%B8%E6%88%8F, 三农=http://zhidao.baidu.com/list?category=%E4%B8%89%E5%86%9C, 生产制造=http://zhidao.baidu.com/list?category=%E7%94%9F%E4%BA%A7%E5%88%B6%E9%80%A0, 金融财经=http://zhidao.baidu.com/list?category=%E9%87%91%E8%9E%8D%E8%B4%A2%E7%BB%8F, 旅游=http://zhidao.baidu.com/list?category=%E6%97%85%E6%B8%B8}

每个分类还有子分类，暂时忽略，因为这里已经可以抓取列表了