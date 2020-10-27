package com.chenbing.inspur.service;

import com.chenbing.inspur.pojo.GzcrmCmregular;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("regularMeetingService")
public interface RegularMeetingService {
    List<GzcrmCmregular> queryRegulaList(String meetingId, String meetingDate, String meetingTheme);

    int insertRegular(GzcrmCmregular cmregular);

    GzcrmCmregular queryRegularById(int reId);

    int updateRegular(GzcrmCmregular cmregular);

    int deleteRegularById(int reId);

    boolean deleteMany(String checkTnum);
}
