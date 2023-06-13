
import { createStore } from 'vuex';

const store = createStore({
    state () {
        return {
            user: {
                id: '',
                username: '',
                password: '',
                email: '',
                type: '',
            },
            colleges: [
                "人工智能与大数据学院",
                "粮油食品学院",
                "土木工程学院",
                "化学化工学院",
                "电气工程学院",
                "经济贸易学院",
                "设计艺术学院",
                "信息科学与工程学院",
                "生物工程学院" ,
                "环境工程学院" ,
                "外语学院",
                "新闻与传播学院",
                "机电工程学院" ,
                "材料科学与工程学院",
                "管理学院",
                "理学院" ,
                    "法学院" ,
                    "国际教育学院" ,
            ],
            types: [
                '电信诈骗',
                '刷单诈骗',
                '网络购物诈骗',
                '冒充熟人诈骗',
                '网络贷款诈骗',
                '网络投资诈骗',
                '冒充公检法诈骗',
                '迷信型诈骗',
                '中奖型诈骗',

            ]
        }
    },
    mutations: {
        changeUser (user, update) {
            user = update
        }
    }
})

export default store