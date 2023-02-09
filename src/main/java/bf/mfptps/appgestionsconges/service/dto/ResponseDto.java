package bf.mfptps.appgestionsconges.service.dto;

import java.io.Serializable;

import bf.mfptps.appgestionsconges.utils.ResponseMessage;

public class ResponseDto implements Serializable{
	
	private ResponseMessage repMessage;
	private Object data;
	
	public ResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseDto(ResponseMessage repMessage, Object data) {
		super();
		this.repMessage = repMessage;
		this.data = data;
	}

	public ResponseMessage getRepMessage() {
		return repMessage;
	}

	public void setRepMessage(ResponseMessage repMessage) {
		this.repMessage = repMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	

}
