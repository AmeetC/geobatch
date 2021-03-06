/*
 *  GeoBatch - Open Source geospatial batch processing system
 *  http://geobatch.geo-solutions.it/
 *  Copyright (C) 2007-2012 GeoSolutions S.A.S.
 *  http://www.geo-solutions.it
 *
 *  GPLv3 + Classpath exception
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.geosolutions.geobatch.octave;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import dk.ange.octave.OctaveEngine;
import dk.ange.octave.type.OctaveString;

/**
 * 
 * @author Carlo Cancellieri - carlo.cancellieri@geo-solutions.it
 *
 */
@XStreamAlias("OctaveString")
public class SerializableOctaveString  extends SerializableOctaveObject<OctaveString>{
    
    //< contains the value of this variable
    @XStreamAlias("value")
    @XStreamAsAttribute
    private String _val;
    
    @XStreamOmitField
    private boolean _sync=false;
    
    public SerializableOctaveString(String name,String val){
        super(name,new OctaveString(val));
        _val=val;
        _sync=true;
    }
    
    @Deprecated
    public String getSerializedValue(){
        return getOctObj().getString();
    }
    
    public String getValue(){
        if (!_sync){
            setVal();
        }
        return getOctObj().getString();
    }

    
    public void reSetVal(String s) {
        if (getOctObj()!=null)
            getOctObj().setString(s);
        else
            setOctObj(new OctaveString(s));
        _val=s;
        _sync=true;
    }
    
    @Override
    public Object clone(){
        return new SerializableOctaveString(new String(this.getName()), new String(this._val));
    }

    /**
     * This is executed by getValue()
     * 
     * @note: this is public since constructor
     * is never called by XStream so, to synchronize
     * variables we need to call it manually.
     */
    @Override
    public void setVal() {
        reSetVal(_val);
    }
    
    /**
     * get the value from the octave env
     */
    @Override
    public OctaveString get(OctaveEngine engine){
        return engine.get(OctaveString.class,this.getName());
    }
    
    @Override
    protected final OctaveString getOctObj(){
        if (super._obj!=null)
            return super._obj;
        else {
            setOctObj(new OctaveString(getName()));
        }
        return super._obj;
    }

    @Override
    protected void setOctObj(OctaveString obj) {
        // TODO check if obj!=null throwing error
        super._obj=obj;
        
    }
    
}
