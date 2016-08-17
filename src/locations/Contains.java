enum Contains {
	CONTAINS(1), NOT_CONTAINS(2), SEMI_CONTAINS(3);
	private int value;
	
	private Contains (int value) {
		this.value = value;
	}
}