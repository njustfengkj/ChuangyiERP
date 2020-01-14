package com.feng.chuangyierp.service.hr.impl

import com.feng.chuangyierp.dao.hr.ShiftDao
import spock.lang.Specification

class ShiftServiceImplTest extends Specification {
    def shiftDao=Mock(ShiftDao)
    def service=new ShiftServiceImpl()
    void cleanup() {
    }

    def "BatchQuery"() {
        given:

        when:

        then:
        shiftDao.delete()
        shiftDao.add()
    }

    def "Delete"() {
    }

    def "Add"() {
    }
}
