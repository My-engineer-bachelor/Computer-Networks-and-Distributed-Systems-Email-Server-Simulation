package util;

import client.Count;

public class Email {
	private Count to,
				from;
	private String content,
					title;		
	private boolean read;		
	
	public Email(Count to, Count from, String title, String content) {
		super();
		this.to = to;
		this.from = from;
		this.content = content;
		this.title = title;
	}	
	
	public boolean isRead(){
		return read;
	}
	
	public void read(){
		read = true;
	}		

	@Override
	public boolean equals(Object obj) {		
		if (obj == null || getClass() != obj.getClass())
			return false;		
		if (this == obj)
			return true;
		Email other = (Email) obj;
		return other.getContent().equals(this.getContent()) && other.getFrom().equals(this.getFrom()) 
				&& other.getTo().equals(this.getTo()) && other.getTitle().equals(this.getTitle());			
	}

	@Override
	public String toString() {
		return "Email [to=" + to + ", from=" + from + ", content=" + content + ", title=" + title + "]";
	}

	public Count getTo() {
		return to;
	}
	public void setTo(Count to) {
		this.to = to;
	}
	public Count getFrom() {
		return from;
	}
	public void setFrom(Count from) {
		this.from = from;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
