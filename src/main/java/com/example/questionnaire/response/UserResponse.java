package com.example.questionnaire.response;

public class UserResponse implements MyResponse {

	private boolean succeed;

	private String msg;
	private String username;
	private int uid;

	private UserResponse(Builder builder) {
		this.succeed = builder.succeed;
		this.msg = builder.msg;
		this.username = builder.username;
		this.uid = builder.uid;
	}

	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public static class Builder {

		private boolean succeed;

		private String msg;
		private String username;
		private int uid;

		public Builder(boolean succeed) {
			this.succeed = succeed;
			this.msg = succeed? "请求成功": "请求失败";
			this.username = "";
		}

		public Builder msg(String val) {
			this.msg = val;
			return this;
		}

		public Builder username(String val) {
			this.username = val;
			return this;
		}

		public Builder uid(int val) {
			this.uid = val;
			return this;
		}

		public UserResponse build() {
			return new UserResponse(this);
		}
	}
}
