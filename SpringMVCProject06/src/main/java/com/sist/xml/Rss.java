package com.sist.xml;

import javax.xml.bind.annotation.XmlRootElement;

// Rss �ȿ��� Channel
@XmlRootElement 
public class Rss {
	private Channel channel=new Channel();

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
