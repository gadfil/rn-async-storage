/**
 * Fetches key and passes the result to callback, along with an Error if there is any.
 */
export function getItem(key: string, callback?: (error?: Error, result?: string) => void): Promise<string | null>;

/**
 * Sets value for key and calls callback on completion, along with an Error if there is any
 */
export function setItem(key: string, value: string, callback?: (error?: Error, result?: string) => void): Promise<void>;

/**
 * Remove value by key
 */
export function removeItem(key: string, callback?: (error?: Error, result?: string) => void): Promise<void>;

/**
 * Remove all values fpr all keys
 */
export function clear(callback?: (error?: Error, result?: string) => void): Promise<void>;

/**
 * Gets all keys known to the app
 */
export function getAllKeys(callback?: (error?: Error, keys?: string[]) => void): Promise<string[]>;


/**
 * multiSet and multiMerge take arrays of key-value array pairs that match the output of multiGet,
 *
 * multiSet([['k1', 'val1'], ['k2', 'val2']], cb);
 */
export function multiSet(keyValuePairs: string[][], callback?: (errors?: Error[]) => void): Promise<void>;

/**
 * multiGet invokes callback with an array of key-value pair arrays that matches the input format of multiSet
 */
export function multiGet(
    keys: string[],
    callback?: (errors?: Error[], result?: [string, string][]) => void
): Promise<[string, string][]>;