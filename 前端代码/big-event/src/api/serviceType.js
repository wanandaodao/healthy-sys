import request from '@/utils/request.js'

export const serviceTypetListService = ()=>{
    return request.get('/serviceType')
}