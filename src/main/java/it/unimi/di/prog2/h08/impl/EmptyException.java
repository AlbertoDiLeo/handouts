/*

Copyright 2024 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

/*
 * APPUNTI
 * La classe EmptyException è un'eccezione personalizzata che viene lanciata quando un'applicazione tenta di estrarre elementi da un IntSet vuoto.
 * Il sotto-package impl è spesso utilizzato per contenere le implementazioni concrete delle interfacce o delle classi astratte definite nel package principale. Questo aiuta a organizzare il codice in modo più modulare e a separare le interfacce dalle loro implementazioni.
 * Nel contesto del package h08, la classe EmptyException potrebbe essere utilizzata per gestire situazioni specifiche in cui un IntSet è vuoto e un'operazione non può essere completata.
 */

package it.unimi.di.prog2.h08.impl;

/**
 * Thrown when an application attempts to use extract elements from an empty {@link
 * it.unimi.di.prog2.h08.IntSet}.
 */
public class EmptyException extends IllegalStateException {
  /*
   * La classe EmptyException estende IllegalStateException perché rappresenta una condizione in cui l'oggetto si trova in uno stato illegale o inappropriato per l'operazione richiesta. In questo caso, l'operazione di estrazione di elementi da un IntSet vuoto è considerata illegale, quindi l'eccezione EmptyException è una specializzazione di IllegalStateException.
   */

  /** THe serial version. */
  static final long serialVersionUID = 1L;
  // serialVersionUID è un campo statico final utilizzato per garantire che le versioni serializzate di un oggetto siano compatibili tra loro. Se si modifica la classe, è necessario aggiornare il valore di serialVersionUID per evitare problemi di serializzazione.

  /** Builds a new exception. */
  public EmptyException() {
    super();
  }
  // Costruttore vuoto che chiama il costruttore della superclasse per creare un'istanza di EmptyException.
  // super() chiama il costruttore della superclasse, che in questo caso è IllegalStateException.
  // Chiama il costruttore della classe base. È necessario per garantire che la classe base sia inizializzata correttamente.
  // se avessi usato new() avrei creato una nuova istanza di IllegalStateException, ma non avrei inizializzato la classe corrente (EmptyException) come una sottoclasse di IllegalStateException.

  /**
   * Builds a new exception with a given message.
   *
   * @param message the message.
   */
  public EmptyException(String message) {
    super(message);
  }
  // Costruttore che accetta un messaggio come argomento e lo passa al costruttore della superclasse per creare un'istanza di EmptyException con un messaggio specifico.

  /**
   * Builds an exception with a given message and cause.
   *
   * @param message the message.
   * @param cause the cause.
   */
  public EmptyException(String message, Throwable cause) {
    super(message, cause);
  }
  // Costruttore che accetta un messaggio e una causa come argomenti e li passa al costruttore della superclasse per creare un'istanza di EmptyException con un messaggio e una causa specifici.
}
