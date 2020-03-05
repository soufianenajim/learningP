package com.learning.model.base;

import java.io.Serializable;
import java.util.Collection;

public final class PartialList<T> implements Serializable {

	/**
	 * Commentaire pour <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 8245327832879376587L;

	private Long count;

	private transient Collection<T> lignes;

	/**
	 * @param count
	 * @param lignes
	 */
	public PartialList(Long count, Collection<T> lignes) {
		this.count = count;
		this.lignes = lignes;
	}

	/**
	 * @return Renvoie count.
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @param count
	 *            count à définir.
	 */
	public void setCount(Long count) {
		this.count = count;
	}

	/**
	 * @return Renvoie lignes.
	 */
	public Collection<T> getLignes() {
		return lignes;
	}

	/**
	 * @param lignes
	 *            lignes à définir.
	 */
	public void setLignes(Collection<T> lignes) {
		this.lignes = lignes;
	}

}

