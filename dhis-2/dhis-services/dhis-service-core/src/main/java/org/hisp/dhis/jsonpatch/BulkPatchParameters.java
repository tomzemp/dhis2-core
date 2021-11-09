/*
 * Copyright (c) 2004-2004-2021, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.hisp.dhis.jsonpatch;

import java.util.List;
import java.util.function.Function;

import lombok.Builder;
import lombok.Data;

import org.hisp.dhis.commons.jackson.jsonpatch.JsonPatch;
import org.hisp.dhis.feedback.ErrorReport;
import org.hisp.dhis.schema.Schema;

import com.google.api.client.util.Lists;

@Data
@Builder
public class BulkPatchParameters
{
    private List<ErrorReport> errorReports;

    private boolean isAtomic;

    private Function<JsonPatch, List<ErrorReport>> patchValidator;

    private Function<Schema, List<ErrorReport>> schemaValidator;

    public List<ErrorReport> getErrorReports()
    {
        return errorReports == null ? Lists.newArrayList() : errorReports;
    }

    public void addErrorReport( ErrorReport errorReport )
    {
        errorReports.add( errorReport );
    }

    public void addErrorReports( List<ErrorReport> errorReports )
    {
        errorReports.addAll( errorReports );
    }

    public boolean hasPatchValidator()
    {
        return patchValidator != null;
    }

    public boolean hasSchemaValidator()
    {
        return schemaValidator != null;
    }
}
