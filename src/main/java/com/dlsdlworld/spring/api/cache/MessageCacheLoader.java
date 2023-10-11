package com.dlsdlworld.spring.api.cache;

import com.dlsdlworld.spring.api.event.BaseMessageListener;
import com.dlsdlworld.spring.api.model.BaseMessage;
import com.dlsdlworld.spring.api.model.Message;
import com.dlsdlworld.spring.api.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/04/02
 * Time : 2:48 오후
 */
@Slf4j
@Service
public class MessageCacheLoader {

	private final MessageRepository messageRepository;

	private final BaseMessageListener baseMessageListener;

	@Autowired
	public MessageCacheLoader(
		MessageRepository messageRepository,
		BaseMessageListener baseMessageListener) {
		this.messageRepository = messageRepository;
		this.baseMessageListener = baseMessageListener;
	}

	/**
	 * 메시지캐시 리로딩
	 */
	public void load() {
		Set<Message> messages = messageRepository.findAll();
		log.info("## messages:{}", messages.size());
		for (Message message : messages) {
			baseMessageListener.saveCache(message);
		}
	}


	/**
	 * 메시지캐시 리로딩
	 */
	public void load(String keyword, String langCd) {
		ArrayList<BaseMessage> messages = (ArrayList<BaseMessage>) Optional.ofNullable(messageRepository.findAllByMsgCdContainingAndLangCd(keyword, langCd, BaseMessage.class)).orElseGet(()-> new ArrayList<>());
		baseMessageListener.deleteCaches(messages);
		log.info("## messages:{}", messages.size());
		baseMessageListener.saveCaches(messages);
	}
}
