
<template>
 <div>
   <el-row justify="center">
     <el-col :span="22">
       <h1 style="font-size: 50px">概览</h1>
       <el-row :gutter="20" justify="space-between">
         <el-col :span="6" align="center">
           <el-card class="Card" shadow="hover" style="height: 25vh">
             <div id="peoplecount" style="height: 25vh;"></div>
           </el-card>
         </el-col>
         <el-col :span="6">
           <el-card class="Card" shadow="hover" style="height: 25vh">
             <div id="fraudamount" style="height: 25vh;"></div>
           </el-card>
         </el-col>
         <el-col :span="6">
           <el-card class="Card" shadow="hover" style="height: 25vh">
             <div id="informationcount" style="height: 25vh;"></div>
           </el-card>
         </el-col>
         <el-col :span="6">
           <el-card class="Card" shadow="hover" style="height: 25vh">
             <div id="accountcount" style="height: 25vh;"></div>
           </el-card>
         </el-col>
       </el-row>
       <el-row :gutter="20" style="margin-top: 30px">
         <el-col :span="16">
           <el-card class="Card" shadow="hover" style="height: 25vh">
             <div id="trend" style="height: 25vh;"></div>
           </el-card>
         </el-col>
         <el-col :span="8">
           <el-card class="Card" shadow="hover" style="height: 25vh">
             <div id="proportion" style="height: 25vh;"></div>
           </el-card>
         </el-col>
       </el-row>
     </el-col>
   </el-row>
 </div>
</template>

<script setup>

import * as echarts from 'echarts'
import axios from "axios";
import {onMounted, reactive, ref} from "vue";


// const peoplecount = reactive({
//   title: {
//     text: 'ECharts 入门示例'
//   },
//   tooltip: {},
//   xAxis: {
//     data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
//   },
//   yAxis: {},
//   series: [
//     {
//       name: '销量',
//       type: 'bar',
//       data: [5, 20, 36, 10, 10, 20]
//     }
//   ]
// })
const until = reactive([
  {
    url: 'http://localhost:8080/api/data/getdifferentpersoncount',
    dom: 'peoplecount',
  },
  {
    url: 'http://localhost:8080/api/data/getfraudamount',
    dom: 'fraudamount'
  },
  {
    url: 'http://localhost:8080/api/data/getinformationcount',
    dom: 'informationcount',
  },
  {
    url: "http://localhost:8080/api/data/getallinformation",
    dom: "trend"
  },
  {
    url: "http://localhost:8080/api/data/getallinformation",
    dom: "proportion"
  },
  {
    url: "http://localhost:8080/api/data/getAllAccountsCount",
    dom: "accountcount",
  }
])
const options = [
  {
    color: '#ec8aa4',
    grid: {show:'true',borderWidth:'0'},
    title: {
      text: "总受骗人数"
    },
    tooltip: {},
    xAxis: {
      data: [" "],
      splitLine: {
        show: false,
      },
    },
    yAxis: {
      type: 'value',
      splitLine: {
        show: false,
      },
    },
    series: [{
      name: "受骗人数",
      type: "bar",
      barWidth: '70%',
      barBorderRadius: 10,
      data: [],
      label: {
        show: true,
        position: 'bottom',
        valueAnimation: true
      }
    }, ],

  },
  {
    grid: {show:'true',borderWidth:'0'},
    title: {
      text: "总受骗金额"
    },
    tooltip: {},
    xAxis: {
      data: [" "],
      splitLine: {
        show: false,
      },
    },
    yAxis: {
      type: 'value',
      splitLine: {
        show: false,
      },
    },
    series: [{
      name: "总受骗金额",
      type: "bar",
      barWidth: '70%',
      barBorderRadius: 10,
      data: [],
      label: {
        show: true,
        position: 'bottom',
        valueAnimation: true
      }
    }, ],

  },
  {
    color: ['#fbda41'],
    grid: {show:'true',borderWidth:'0'},
    title: {
      text: "总诈骗案例数"
    },
    tooltip: {},
    xAxis: {
      data: [" "],
      splitLine: {
        show: false,
      },
    },
    yAxis: {
      type: 'value',
      splitLine: {
        show: false,
      },
    },
    series: [
      {
        name: "总诈骗案例数",
        type: "bar",
        barWidth: '70%',
        barBorderRadius: 10,
        data: [],
        label: {
          show: true,
          position: 'bottom',
          valueAnimation: true
        }
      },
    ],
  },
  {
    color: '#57c3c2',
    title: {
      text: "趋势图",
    },
    xAxis: {
      type: 'category',
      data: [" "]
    },
    tooltip: {},
    yAxis: {
      type: 'value'
    },
    series: [
      {
        name: "受骗金额",
        type: 'line',
        smooth: true,
        areaStyle: {},
        data: [],
        label: {
          show: true,
          position: 'top',
          valueAnimation: true
        }
      }
    ]
  },
  {

    title: {
      text: "各类诈骗占比"
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      top: '5%',
      left: 'center'
    },
    series: [{
      type: 'pie',
      radius: ['40%', '70%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 10,
        borderColor: '#fff',
        borderWidth: 2
      },
      label: {
        show: false,
        position: 'center'
      },
      emphasis: {
        label: {
          show: true,
          fontSize: 25,
          fontWeight: 'bold'
        }
      },
      labelLine: {
        show: false
      },
      data: [],
    }]
  },
  {
    color: ['#80FFA5'],
    grid: {show:'true',borderWidth:'0'},
    title: {
      text: "用户数"
    },
    tooltip: {},
    xAxis: {
      data: [" "],
      splitLine: {
        show: false,
      },
    },
    yAxis: {
      type: 'value',
      splitLine: {
        show: false,
      },
    },
    series: [{
      name: "用户数",
      type: "bar",
      barWidth: '70%',
      barBorderRadius: 10,
      data: [],
      label: {
        show: true,
        position: 'bottom',
        valueAnimation: true
      }
    }, ],

  },
]

// 数据处理函数
function processData(data) {
  var categories = [];
  var values = [];

  // 遍历数据，按天统计金额之和
  data.forEach(function(item) {
    var date = new Date(item.time);
    var day = date.getMonth() + 1 + '-' + date.getDate();

    // 检查日期是否已存在于 categories 数组中
    var index = categories.indexOf(day);

    if (index === -1) {
      // 日期不存在，将日期添加到 categories 数组，金额添加到 values 数组
      categories.push(day);
      values.push(item.amount);
    } else {
      // 日期已存在，累加金额
      values[index] += item.amount;
    }
  });

  return {
    categories: categories,
    values: values
  };
}

// 数据处理函数
function processTypeData(data) {
  var result = [];

  // 统计每种类型的数据数量
  var countMap = {};
  data.forEach(function(item) {
    if (countMap[item.type]) {
      countMap[item.type]++;
    } else {
      countMap[item.type] = 1;
    }
  });

  // 将统计结果转换为符合 ECharts 要求的格式
  for (var type in countMap) {
    result.push({
      name: type,
      value: countMap[type]
    });
  }

  return result;
}
function refreshData(chart, data){
  //刷新数据
  var option = chart.getOption();
  option.series[0].data[0] = data;
  chart.setOption(option);
}
const initChart = () => {
  const chartContainer = []
  const chart = []

  for (let item in options) {
    chartContainer[item] = document.getElementById(until[item].dom)
    chart[item] = echarts.init(chartContainer[item])
    chart[item].setOption(options[item], true)
    // 添加窗口改变监听
    chart[item]._resize = () => {
      chart[item].resize()
    }
    window.addEventListener('resize', chart[item]._resize)
  }
  for (let item in options) {
    axios
        .get(until[item].url)
        .then((res) => {
          if (item === '3') {
            let option = chart[item].getOption()
            let data = processData(res.data.data)
            // console.log(data)
            option.xAxis[0].data = data.categories
            option.series[0].data = data.values
            console.log(option)
            chart[item].setOption(option);
          } else if (item === '4') {
            let option = chart[item].getOption()
            let data = processTypeData(res.data.data)
            console.log(data)
            option.series[0].data = data
            chart[item].setOption(option);
          } else {
            refreshData(chart[item], res.data.data)
          }


        })
  }
}
onMounted(() => {
  initChart()
})

</script>

<style scoped>
.Card {
  background-color: white;
  border-radius: 12px
}
</style>