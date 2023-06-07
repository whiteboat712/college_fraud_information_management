import information from '@/api/request'

const informationApi = data =>  {
    return information.get({
        url: '/api/data',
        data
    })
}

export default {
    informationApi
}

